package com.bnb.grab.view

import com.bnb.grab.common.BaseView

/**
 * Created by wsl on 2018/1/8.
 */

open interface IDetailView : BaseView {
    interface IViewView:IDetailView {

    }

    interface ICodeView:IDetailView {

    }
}
