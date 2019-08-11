package pl.sda.javawwa20;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class UserTDD {

    private LocalDateTime registeredDate;
    private UserTDDStatus status;
    private LocalDateTime lastLoggedInDate;

    //redefiniuje domyslny konstruktor
    public UserTDD() {
        this.registeredDate = LocalDateTime.now();
        this.status = UserTDDStatus.INITIALIZED;
    }

    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
    }

    public UserTDDStatus getStatus() {
        return status;
    }

    public void setStatus(UserTDDStatus status) {
        this.status = status;
    }

    public Period calcTimeSinceRegistered() {
        return Period.between(this.registeredDate.toLocalDate(), LocalDate.now());
    }

    public void login() {
        this.lastLoggedInDate = LocalDateTime.now();
    }

    public LocalDateTime getLastLoggedInDate() {
        return lastLoggedInDate;
    }

    public void setLastLoggedInDate(LocalDateTime lastLoggedInDate) {
        this.lastLoggedInDate = lastLoggedInDate;
    }

    public Duration calcTimeSinceLastLoggedIn() {
        return Duration.between(this.lastLoggedInDate, LocalDateTime.now());
    }

    //inner class
    public enum UserTDDStatus {
        INITIALIZED

    }
}
