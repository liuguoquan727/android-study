package com.liuguoquan.module.library.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.library.R;
import com.liuguoquan.module.library.R2;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.liuguoquan.commom.base.Constants.TAG;
import static io.reactivex.Observable.create;

/**
 * RxJava2使用
 * Created by liuguoquan on 2017/6/28.
 */

public class RxJavaFragment extends AppBaseFragment {

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override public int getContentView() {
    return R.layout.module_library_fragment_rxjava;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "RxJava2";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override public void initView(View view) {
    requestBaseInit(getPageTitle());
  }

  @OnClick({
      R2.id.general, R2.id.thread, R2.id.map, R2.id.flatmap, R2.id.concatmap, R2.id.zip,
      R2.id.filter, R2.id.sample
  }) public void onClick(View view) {
    int id = view.getId();
    if (id == R.id.general) {
      genneral();
    } else if (id == R.id.thread) {
      doThread();
    } else if (id == R.id.map) {
      doMap();
    } else if (id == R.id.flatmap) {
      doFlatMap();
    } else if (id == R.id.concatmap) {
      doConcatMap();
    } else if (id == R.id.zip) {
      doZip();
    } else if (id == R.id.filter) {
      doFilter();
    } else if (id == R.id.sample) {
      doSample();
    }
  }

  /**
   * filter操作符只允许符合要求的上游事件发送给下游
   */
  private void doFilter() {
    Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        for (int i = 0; i < 100; i++) {  //模拟无限循环发送事件
          emitter.onNext(i);
        }
      }
    }).subscribeOn(Schedulers.io()).filter(new Predicate<Integer>() {
      @Override public boolean test(@NonNull Integer integer) throws Exception {
        return integer.intValue() % 10 == 0;
      }
    }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
      @Override public void accept(Integer integer) throws Exception {
        Log.d(TAG, "" + integer);
      }
    });
  }

  /**
   * sample操作符每隔指定的时间就从上游中取出一个事件发送给下游.
   */
  private void doSample() {
    Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        for (int i = 0; i < 1000; i++) {  //模拟无限循环发送事件
          emitter.onNext(i);
        }
      }
    })
        .subscribeOn(Schedulers.io())
        .sample(1, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Integer>() {
          @Override public void accept(Integer integer) throws Exception {
            Log.d(TAG, "" + integer);
          }
        });
  }

  /**
   * Zip通过一个函数将多个Observable发送的事件结合到一起，然后发送这些组合到一起的事件.
   * 它按照严格的顺序应用这个函数。它只发射与发射数据项最少的那个Observable一样多的数据。
   *
   * 应用：信息分别要从两个服务器接口中获取, 而只有当两个都获取到了之后才能进行展示
   */
  private void doZip() {
    Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
      @Override public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
        e.onNext("1");
        e.onNext("2");
        e.onNext("3");
        e.onNext("4");
        Log.d(TAG, "onNext: " + "4");
        e.onComplete();
      }
    }).subscribeOn(Schedulers.io());
    Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
      @Override public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
        e.onNext("A");
        e.onNext("B");
        e.onNext("C");
      }
    }).subscribeOn(Schedulers.io());

    Observable.zip(observable1, observable2, new BiFunction<String, String, String>() {
      @Override public String apply(@NonNull String s, @NonNull String s2) throws Exception {
        return s + s2;
      }
    }).subscribe(new Consumer<String>() {
      @Override public void accept(@NonNull String s) throws Exception {
        Log.d(TAG, "accept: " + s);
      }
    });
  }

  /**
   * ConcatMap将一个发送事件的上游Observable变换为多个发送事件的Observables，然后将它们发射的事件合并后放进一个单独的Observable里.
   * ConcatMap保证事件的顺序,
   */
  private void doConcatMap() {
    create(new ObservableOnSubscribe<String>() {
      @Override public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
        e.onNext("liu");
        e.onNext("lee");
        e.onNext("zhang");
      }
    }).concatMap(new Function<String, ObservableSource<String>>() {
      @Override public ObservableSource<String> apply(@NonNull String s) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("My name 1 is " + s);
        list.add("My name 2 is " + s);
        return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
      }
    }).subscribe(new Consumer<String>() {
      @Override public void accept(@NonNull String s) throws Exception {
        Log.d(TAG, "accept: " + s);
      }
    });
  }

  /**
   * FlatMap将一个发送事件的上游Observable变换为多个发送事件的Observables，然后将它们发射的事件合并后放进一个单独的Observable里.
   * flatMap并不保证事件的顺序,
   */
  private void doFlatMap() {
    create(new ObservableOnSubscribe<String>() {
      @Override public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
        e.onNext("liu");
        e.onNext("lee");
        e.onNext("zhang");
      }
    }).flatMap(new Function<String, ObservableSource<String>>() {
      @Override public ObservableSource<String> apply(@NonNull String s) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("My name 1 is " + s);
        list.add("My name 2 is " + s);
        return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
      }
    }).subscribe(new Consumer<String>() {
      @Override public void accept(@NonNull String s) throws Exception {
        Log.d(TAG, "accept: " + s);
      }
    });
  }

  /**
   * map作用就是对上游发送的每一个事件应用一个函数, 使得每一个事件都按照指定的函数去变化.
   */
  private void doMap() {

    create(new ObservableOnSubscribe<String>() {

      @Override public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
        e.onNext("liu");
      }
    }).map(new Function<String, String>() {

      @Override public String apply(@NonNull String s) throws Exception {
        return "My name is " + s;
      }
    }).subscribe(new Consumer<String>() {
      @Override public void accept(@NonNull String s) throws Exception {
        Log.d(TAG, "accept: " + s);
      }
    });
    //accept: My name is liu
  }

  /**
   * ObservableEmitter 就是用来发出事件的，它可以发出三种类型的事件，通过调用emitter的onNext(T value)、onComplete()和onError(Throwable
   * error)就可以分别发出next事件、complete事件和error事件。
   * Disposable
   * 基本使用
   */
  private void genneral() {

    create(new ObservableOnSubscribe<String>() {

      @Override public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
        e.onNext("1");
        e.onNext("2");
        e.onNext("3");
        e.onComplete();
      }
    }).subscribe(new Observer<String>() {

      private Disposable mDisposable;

      @Override public void onSubscribe(@NonNull Disposable d) {
        Log.d(TAG, "onSubscribe: ");
        mDisposable = d;
      }

      @Override public void onNext(@NonNull String s) {
        Log.d(TAG, "onNext: " + s);
        if (s.equals("2")) {
          //丢弃后 ObservableEmitter仍发送事件 但是 subscribe不再接收事件
          mDisposable.dispose();
        }
      }

      @Override public void onError(@NonNull Throwable e) {
        Log.d(TAG, "onError: " + e.getMessage());
      }

      @Override public void onComplete() {
        Log.d(TAG, "onComplete: ");
      }
    });

    //第二种写法
    create(new ObservableOnSubscribe<String>() {

      @Override public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
        e.onNext("1");
        e.onNext("2");
        e.onNext("3");
        e.onComplete();
      }
    }).subscribe(new Consumer<String>() {
      @Override public void accept(@NonNull String s) throws Exception {
        Log.d(TAG, "accept: " + s);
      }
    }, new Consumer<Throwable>() {
      @Override public void accept(@NonNull Throwable throwable) throws Exception {

      }
    });
  }

  /**
   * RxJava线程控制
   * subscribeOn() 指定的是上游发送事件的线程,
   * observeOn() 指定的是下游接收事件的线程.
   * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
   * 多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
   */
  private void doThread() {

    //事件发送
    Observable<String> observable = create(new ObservableOnSubscribe<String>() {
      @Override public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
        Log.d(TAG, "observable thread is: " + Thread.currentThread().getName());
        e.onNext("hello world");
      }
    });
    //事件消费
    Consumer<String> consumer = new Consumer<String>() {
      @Override public void accept(@NonNull String s) throws Exception {
        Log.d(TAG, "consumer thread is: " + Thread.currentThread().getName());
        Log.d(TAG, "accept: " + s);
      }
    };
    //订阅消息
    //默认在同一个线程
    observable.subscribe(consumer);

    //事件发送在子线程  事件消费在主线程
    observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(consumer);
  }
}
