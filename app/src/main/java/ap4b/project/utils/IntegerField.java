package ap4b.project;

import javafx.scene.control.*;
import javafx.beans.value.*;

public class IntegerField extends TextField {
    public int min;
    public int max;

    private void setupListener() {
        IntegerField self = this;

        this.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("^\\d*$")) {
                    self.setText(newValue.replaceAll("\\D", ""));
                }
            }
        });
    }

    IntegerField(int min, int max) {
        super(Integer.toString(min));
        this.setPrefWidth((Math.ceil(Math.log10(max)) + 2) * 14);
        this.min = min;
        this.max = max;

        this.setupListener();
    }

    IntegerField(int min, int max, int def) {
        super(Integer.toString(def));
        this.setPrefWidth((Math.ceil(Math.log10(max)) + 2) * 14);
        this.min = min;
        this.max = max;

        this.setupListener();
    }

    public int getValue() {
        Integer tmp = Integer.parseInt(this.getText());
        if (tmp == null || tmp < this.min) {
            return this.min;
        } else if (tmp > this.max) {
            return this.max;
        } else {
            return tmp;
        }
    }
}
