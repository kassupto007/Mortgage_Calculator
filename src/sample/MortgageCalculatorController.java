package sample;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class MortgageCalculatorController {

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

    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat getYears = NumberFormat.getInstance();
    private BigDecimal years = new BigDecimal(10);
    private BigDecimal hundred = new BigDecimal(100);
    private BigDecimal twelve = new BigDecimal(12);
    private BigDecimal one = new BigDecimal(1);



    @FXML
    private Slider yearSlider;
    public void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);
        yearSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    years = BigDecimal.valueOf(newValue.intValue());
                    yearLabel.setText(getYears.format(years));
                }
        );
    }

    @FXML
    void calculateButtonPressed() {
        String stringPurchasePrice = purchasePriceTextField.getText();
        BigDecimal purchasePrice = new BigDecimal(stringPurchasePrice);
        BigDecimal purchasePrice1 = new BigDecimal(String.valueOf(purchasePrice));
        String stringDownPayment = downPaymentTextField.getText();
        BigDecimal downPayment = new BigDecimal(stringDownPayment);
        BigDecimal loanAmount = purchasePrice1.subtract(downPayment);
        loanAmountTextField.setText(getYears.format(loanAmount));

        String hi = yearLabel.getText();
        int jk = Integer.parseInt(hi);


        BigDecimal getYearLabel = new BigDecimal(jk);
        BigDecimal paymentNumber =  getYearLabel.multiply(twelve);

        String userRate = interestRateTextField.getText();
        BigDecimal Rate =  new BigDecimal(userRate);
        BigDecimal ratePerMonthEx = Rate.divide(hundred);
        BigDecimal ratePerMonth = ratePerMonthEx.divide(twelve);

        //int roundedNumberOfPayments= paymentNumber.intValue();

        int roundedNumberOfPayments = jk * 12;


        BigDecimal term = one.add(ratePerMonth);
        BigDecimal monthlyPayment1 = ratePerMonth.multiply(loanAmount);
        BigDecimal monthlyPayment2 = term.pow(roundedNumberOfPayments);
        BigDecimal numerator = monthlyPayment1.multiply(monthlyPayment2);
        loanAmountTextField.setText(getYears.format(numerator));



//        BigDecimal denominator = monthlyPayment2.subtract(one);
//        BigDecimal monthlyPayment = numerator.divide(denominator);
//        totalTextField.setText(getYears.format(monthlyPayment));
    }
}