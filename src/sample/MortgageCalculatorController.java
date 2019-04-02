package sample;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class MortgageCalculatorController {


    public void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);
        yearSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {

                    years = BigDecimal.valueOf(newValue.intValue());
                    yearLabel.setText(year.format(years));
                }
        );
    }

    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    //private static final NumberFormat percent = NumberFormat.getPercentInstance();
    private static final NumberFormat year = NumberFormat.getInstance();

    private BigDecimal years = new BigDecimal(10);
    private BigDecimal hundred = new BigDecimal(100);
    private BigDecimal twelve = new BigDecimal(12);
    private BigDecimal one = new BigDecimal(1);



    @FXML
    private Label yearLabel;

    @FXML
    private TextField purchasePriceTextField;

    @FXML
    private TextField downPaymentTextField;

    @FXML
    private TextField interestRateTextField;

    @FXML
    private TextField loanAmountTextField;

    @FXML
    private TextField totalTextField;

    @FXML
    private Slider yearSlider;

    @FXML
    void calculateButtonPressed(ActionEvent event) {

        //Sliders
        BigDecimal slidervalue = new BigDecimal(yearSlider.getValue());
        BigDecimal n = slidervalue.multiply(twelve);
        //Change n from bigDecimal to integer because pow only takes integer
        int kazi = n.intValue();

        //VVVVVVVVVVV
        BigDecimal purchase_price = new BigDecimal(purchasePriceTextField.getText());
        BigDecimal down_payment = new BigDecimal(downPaymentTextField.getText());
        BigDecimal loan_amount = purchase_price.subtract(down_payment);
        loanAmountTextField.setText(currency.format(loan_amount));
        BigDecimal rate = new BigDecimal(interestRateTextField.getText());
        BigDecimal interest_rate = rate.divide(hundred);
        BigDecimal r = interest_rate.divide(twelve);

        BigDecimal numofpayments = twelve.multiply(years);



        BigDecimal ex = one.add(r);
        BigDecimal exponent = ex.pow(kazi);
        BigDecimal numerator = loan_amount.multiply(exponent);


        BigDecimal fx = one.subtract(r);
        BigDecimal expon2 = fx.pow(kazi);
        BigDecimal denomerator = expon2.subtract(one);

        BigDecimal whole = numerator.divide(denomerator);

        totalTextField.setText(currency.format(whole));


    }

}