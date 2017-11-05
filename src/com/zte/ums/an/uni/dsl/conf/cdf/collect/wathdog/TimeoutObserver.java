package com.zte.ums.an.uni.dsl.conf.cdf.collect.wathdog;

/**
 * Interface for classes that want to be notified by Watchdog.
 *
 * @since Ant 1.5
 *
 * @see org.apache.tools.ant.util.Watchdog
 *
 */
public interface TimeoutObserver
{

    /**
     * Called when the watchdow times out.
     *
     * @param w the watchdog that timed out.
     */
    void timeoutOccured(Watchdog w);
}