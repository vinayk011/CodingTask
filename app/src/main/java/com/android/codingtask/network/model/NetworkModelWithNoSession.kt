package com.android.codingtask.network.model

import android.app.Application



abstract class NetworkModelWithNoSession<Type : Any, Req, Res> protected constructor(application: Application) : NetworkModel<Type, Req, Res>(application, false)