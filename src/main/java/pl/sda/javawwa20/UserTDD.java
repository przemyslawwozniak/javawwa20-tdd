package pl.sda.javawwa20;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserTDD {

    private LocalDateTime registeredDate;
    private UserTDDStatus status;
    private LocalDateTime lastLoggedInDate;
    private String username;

    public static Map<UserTDDStatus, UserTDDStatus> ALLOWED_STATUS_TRANSITIONS;
    //blok statycznej inicjalizacji kodu
    //zostanie wywolany zanim powstanie jakakolwiek instancja tej klasy
    {
        ALLOWED_STATUS_TRANSITIONS = new HashMap<>();
        ALLOWED_STATUS_TRANSITIONS.put(UserTDDStatus.INITIALIZED,
                UserTDDStatus.ACTIVATED);
        ALLOWED_STATUS_TRANSITIONS.put(UserTDDStatus.ACTIVATED,
                UserTDDStatus.DEACTIVATED);
        ALLOWED_STATUS_TRANSITIONS.put(UserTDDStatus.DEACTIVATED,
                UserTDDStatus.ACTIVATED);
    }

    //redefiniuje domyslny konstruktor
    public UserTDD() {
        this.registeredDate = LocalDateTime.now();
        this.status = UserTDDStatus.INITIALIZED;
    }

    public UserTDD(String username) {
        this();
        this.username = username;
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

    public void printLastLoggedInText() {
        System.out.println(getLastLoggedInText());
    }

    String getLastLoggedInText() {
        return username + "@" + lastLoggedInDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void switchState(UserTDDStatus status) {
        if(ALLOWED_STATUS_TRANSITIONS.get(this.status).equals(status))
            this.status = status;
        else
            throw new StatusTransitionNotPossibleException();
    }

    //inner class
    public enum UserTDDStatus {
        ACTIVATED, DEACTIVATED, INITIALIZED

    }
}
