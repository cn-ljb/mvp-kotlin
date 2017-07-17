package com.ljb.rxjava.kotlin.log

import android.util.Log
import com.wuba.weizhang.common.LV_LOG

class XgoLog @Throws(InstantiationException::class)

private constructor() {

    init {
        throw InstantiationException("This class is not created for instantiation")
    }

    companion object {

        /** 是否允许输出log
         * -1：  不允许
         * 其他：根据等级允许
         */
        private val mDebuggable = LV_LOG

        /** 日志输出时的TAG  */

        private val mTag = "xgo"


        /** 日志输出级别NONE  */
        val LEVEL_NONE = 0


        /** 日志输出级别V  */

        val LEVEL_VERBOSE = 1

        /** 日志输出级别D  */

        val LEVEL_DEBUG = 2

        /** 日志输出级别I  */

        val LEVEL_INFO = 3

        /** 日志输出级别W  */

        val LEVEL_WARN = 4

        /** 日志输出级别E  */

        val LEVEL_ERROR = 5

        /** 以级别为v 的形式输出LOG  */

        fun v(msg: String?) {

            if (mDebuggable >= LEVEL_VERBOSE) {

                Log.v(mTag, msg)

            }

        }

        /** 以级别为 d 的形式输出LOG  */

        fun d(msg: String?) {

            if (mDebuggable >= LEVEL_DEBUG) {

                Log.d(mTag, msg)

            }

        }

        /** 以级别为 i 的形式输出LOG  */

        fun i(msg: String?) {

            if (mDebuggable >= LEVEL_INFO) {

                Log.i(mTag, msg)

            }

        }

        /** 以级别为 w 的形式输出LOG  */

        fun w(msg: String?) {

            if (mDebuggable >= LEVEL_WARN) {

                Log.w(mTag, msg)

            }

        }

        /** 以级别为 w 的形式输出Throwable  */

        fun w(tr: Throwable) {

            if (mDebuggable >= LEVEL_WARN) {

                Log.w(mTag, "", tr)

            }

        }

        /** 以级别为 w 的形式输出LOG信息和Throwable  */

        fun w(msg: String?, tr: Throwable) {

            if (mDebuggable >= LEVEL_WARN && null != msg) {

                Log.w(mTag, msg, tr)

            }

        }

        /** 以级别为 e 的形式输出LOG  */

        fun e(msg: String?) {

            if (mDebuggable >= LEVEL_ERROR) {

                Log.e(mTag, msg)

            }

        }

        /** 以级别为 e 的形式输出Throwable  */

        fun e(tr: Throwable) {

            if (mDebuggable >= LEVEL_ERROR) {

                Log.e(mTag, "", tr)

            }

        }

        /** 以级别为 e 的形式输出LOG信息和Throwable  */

        fun e(msg: String?, tr: Throwable) {

            if (mDebuggable >= LEVEL_ERROR && null != msg) {

                Log.e(mTag, msg, tr)

            }

        }
    }

}
