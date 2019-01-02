package com.bw.student.mvp.base;
import java.lang.ref.WeakReference;

/**
 * @author Administrator QQ:1228717266
 * @name DimensionTech
 * @class name：com.wd.tech.mvp.base
 * @time 2018/11/29 15:31
 */
public abstract class BasePresenter<P extends BaseView> {

    private WeakReference<P> weakReference;

    public P getView() {
        return weakReference.get();
    };

    boolean isAttachView() {
        return weakReference.get() != null && weakReference != null;
    };

    public void AttachView(P p) {
        weakReference = new WeakReference(p);
    }

    public void Destory() {
        if (isAttachView()) {
            weakReference.clear();
            weakReference = null;
        }
    }




}