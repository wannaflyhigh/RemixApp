package com.wannaflyhigh.myapp.service

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class LockAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Not needed for locking
    }

    override fun onInterrupt() {
        // Required method implementation
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
    }

    override fun onDestroy() {
        super.onDestroy()
        if (instance == this) {
            instance = null
        }
    }

    fun lockScreen(): Boolean {
        return performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)
    }

    companion object {
        private var instance: LockAccessibilityService? = null

        fun isRunning(): Boolean = instance != null

        fun requestLock(): Boolean {
            return instance?.lockScreen() ?: false
        }
    }
}
