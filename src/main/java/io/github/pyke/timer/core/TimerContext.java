package io.github.pyke.timer.core;

import java.time.Duration;

public class TimerContext {
    private long targetMillis; // 타이머가 종료될 목표 시간
    private long pausedRemaining; // 일시정지 시 계산된 남은 시간
    private long initialMillis; // 최초 설정 시간
    private TimerState state = TimerState.STOP; // 현재 타이머 상태를 나타내는 열거형 변수

    public void setTime(Duration duration) {
        this.initialMillis = duration.toMillis(); // 최초 설정된 시간을 저장
        this.targetMillis = System.currentTimeMillis() + duration.toMillis(); // 현재 시간 + 설정된 시간
        this.state = TimerState.SET;
    }

    public void start() {
        if (state == TimerState.PAUSED) { this.targetMillis = System.currentTimeMillis() + pausedRemaining; } // 일시정지 상태였으면 목표시간 재설정
        else if (state == TimerState.SET) { this.targetMillis = System.currentTimeMillis() + initialMillis; }
        this.state = TimerState.RUNNING;
    }

    public void pause() {
        if (state == TimerState.RUNNING) {
            this.pausedRemaining = getRemainingMillis(); // 현재 남은 시간을 저장
            this.state = TimerState.PAUSED;
        }
    }

    public void stop() {
        this.targetMillis = 0;
        this.pausedRemaining = 0;
        this.initialMillis = 0;
        this.state = TimerState.STOP;
    }

    public long getRemainingMillis() {
        return switch(state) {
            case RUNNING -> Math.max(0, targetMillis - System.currentTimeMillis()); // 실행 중이면 실시간으로 계산
            case PAUSED -> pausedRemaining; // 일시정지 상태면 저장된 시간 반환
            default -> 0;
        };
    }

    public long getInitialMillis() {
        return initialMillis;
    }

    public void addTime(Duration duration) {
        this.targetMillis += duration.toMillis();
        this.initialMillis += duration.toMillis();
    }

    public void subtractTime(Duration duration) {
        this.targetMillis -= duration.toMillis();
        this.initialMillis -= duration.toMillis();
        if (initialMillis < 1) { initialMillis = 1; }
    }

    public boolean isFinished() {
        return state == TimerState.RUNNING && getRemainingMillis() <= 0;
    }

    public TimerState getState() {
        return state;
    }
}
