module sample.one_time_pad {
    requires javafx.controls;
    requires javafx.fxml;


    opens sample.one_time_pad to javafx.fxml;
    exports sample.one_time_pad;
}