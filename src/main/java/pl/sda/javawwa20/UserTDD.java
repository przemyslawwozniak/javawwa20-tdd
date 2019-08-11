package pl.sda.javawwa20;

import java.time.LocalDateTime;

public class UserTDD {

    private LocalDateTime registeredDate;
    private UserTDDStatus status;

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

    //inner class
    public enum UserTDDStatus {
        INITIALIZED

    }
}
