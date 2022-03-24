package com.william.kotlinsimpletest.activity.plv_test

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.plv.business.model.video.PLVBaseVideoParams
import com.plv.business.model.video.PLVLiveVideoParams
import com.plv.foundationsdk.config.PLVPlayOption
import com.plv.livescenes.feature.login.IPLVSceneLoginManager
import com.plv.livescenes.feature.login.PLVLiveLoginResult
import com.plv.livescenes.feature.login.PLVSceneLoginManager
import com.plv.livescenes.socket.PLVSocketWrapper
import com.plv.livescenes.video.PLVLiveVideoView
import com.plv.socket.impl.PLVSocketManager
import com.plv.socket.net.model.PLVSocketLoginVO
import com.plv.socket.socketio.PLVSocketIOClient
import com.plv.socket.status.PLVSocketStatus
import com.plv.socket.user.PLVSocketUserConstant
import com.william.kotlinsimpletest.BuildConfig
import com.william.kotlinsimpletest.R
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MultiCameraTestActivity : AppCompatActivity() {
    companion object {
        private const val appId = BuildConfig.APP_ID
        private const val appSecret = BuildConfig.APP_SECRET
        private const val userId = BuildConfig.USER_ID
        private const val channelId = "1183415"
        private const val channelId2 = "2080200"
        private const val viewerId = "hwilliamgo-id"
        private const val viewerName = "hwilliamgo-name"
    }


    private val videoView: PLVLiveVideoView by lazy { findViewById(R.id.multi_camera_video_view) }
    private val tvChatMsg: TextView by lazy { findViewById(R.id.muti_camera_tv_chat_msg) }
    private val scrollView: ScrollView by lazy { findViewById(R.id.multi_camera_scroll) }

    private val loginManager: IPLVSceneLoginManager by lazy { PLVSceneLoginManager() }
    private var curChannel = channelId
    private val socketUsedChannel = channelId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_camera_test)

        lifecycleScope.launch {
            loginLive(loginManager)
            loginSocket()
            observeChatMsg()
            playVideo(videoView, curChannel)
        }
    }

    private suspend fun loginLive(loginManager: IPLVSceneLoginManager): PLVLiveLoginResult {
        return suspendCoroutine<PLVLiveLoginResult> {
            loginManager.loginLiveNew(
                appId,
                appSecret,
                userId,
                curChannel,
                object : IPLVSceneLoginManager.OnLoginListener<PLVLiveLoginResult> {
                    override fun onLoginSuccess(p0: PLVLiveLoginResult?) {
                        it.resume(p0!!)
                    }

                    override fun onLoginFailed(p0: String?, p1: Throwable?) {
                        it.resumeWithException(Exception(p1))
                    }
                });
        }
    }

    private suspend fun loginSocket(): Unit {
        return suspendCoroutine {
            //设置socket登录配置
            PLVSocketManager.getInstance().socketObserver.addOnConnectStatusListener { status ->
                if (socketUsedChannel == PLVSocketManager.getInstance().loginVO.channelId) {
                    when (status.status) {
                        PLVSocketStatus.STATUS_LOGINFAIL -> {
                            LogUtils.d("STATUS_LOGINFAIL")
                            it.resumeWithException(Exception("STATUS_LOGINFAIL"))
                        }
                        PLVSocketStatus.STATUS_LOGINING -> {
                            LogUtils.d("STATUS_LOGINING")
                        }
                        PLVSocketStatus.STATUS_LOGINSUCCESS -> {
                            LogUtils.d("STATUS_LOGINSUCCESS")
                            it.resume(Unit)
                        }
                        PLVSocketStatus.STATUS_RECONNECTING -> {
                            LogUtils.d("STATUS_RECONNECTING")
                        }
                        PLVSocketStatus.STATUS_RECONNECTSUCCESS -> {
                            LogUtils.d("STATUS_RECONNECTSUCCESS")
                        }
                        else -> {
                        }
                    }
                }
            }

            //设置socket登录配置
            PLVSocketIOClient.getInstance().setSocketUserId(viewerId) //用户id
                .setNickName(viewerName) //用户昵称
                .setUserType(PLVSocketUserConstant.USERTYPE_STUDENT) //用户类型
                .setChannelId(socketUsedChannel);
            //登录socket
            PLVSocketWrapper.getInstance().login(PLVSocketLoginVO.createFromUserClient())
        }
    }

    private fun observeChatMsg() {
        //添加socket信息监听器
        PLVSocketWrapper.getInstance().socketObserver.addOnMessageListener { listenEvent, event, message ->
            if (socketUsedChannel == PLVSocketWrapper.getInstance().loginVO.channelId) {
                tvChatMsg.apply {
                    append("$message\n")
                    post {
                        scrollView.fullScroll(View.FOCUS_DOWN)
                    }
                }
            }
        }
    }

    private fun playVideo(videoView: PLVLiveVideoView, channelId: String) {
        val liveVideoParams = PLVLiveVideoParams(
            channelId,
            userId,
            viewerId
        )
        liveVideoParams.buildOptions(PLVBaseVideoParams.WAIT_AD, true)
            .buildOptions(PLVBaseVideoParams.HEAD_AD, false)
            .buildOptions(PLVBaseVideoParams.MARQUEE, true)
            .buildOptions(PLVBaseVideoParams.PARAMS2, viewerName)
        videoView.playByMode(liveVideoParams, PLVPlayOption.PLAYMODE_LIVE)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.muti_camera_btn_change_video_channel -> {
                if (curChannel == channelId) {
                    curChannel = channelId2
                } else {
                    curChannel = channelId
                }
                tvChatMsg.append("切换频道->$curChannel")

                playVideo(videoView, curChannel)
            }
        }
    }
}