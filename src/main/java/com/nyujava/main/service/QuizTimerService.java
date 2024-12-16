package com.nyujava.main.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.*;

@Service
public class QuizTimerService {

    private final ConcurrentHashMap<String, ScheduledFuture<?>> userTimers = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * Starts a timer for a user and runs a callback when time is up.
     *
     * @param userId            Unique ID for the user or session.
     * @param durationInSeconds Duration of the quiz in seconds.
     * @param onTimeUpCallback  The task to run when time is up.
     */
    public void startTimer(String userId, int durationInSeconds, Runnable onTimeUpCallback) {
        ScheduledFuture<?> future = scheduler.schedule(() -> {
            onTimeUpCallback.run();
            userTimers.remove(userId); // Remove timer after it expires
        }, durationInSeconds, TimeUnit.SECONDS);
        userTimers.put(userId, future);
    }

    /**
     * Cancels a running timer for the given user.
     *
     * @param userId Unique ID for the user or session.
     */
    public void cancelTimer(String userId) {
        ScheduledFuture<?> future = userTimers.get(userId);
        if (future != null) {
            future.cancel(true);
            userTimers.remove(userId);
        }
    }

    /**
     * Shuts down the scheduler when the application stops.
     */
    public void shutdown() {
        scheduler.shutdown();
    }
}
