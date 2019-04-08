package sample;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import static java.lang.Math.pow;

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
        double purchasePrice = Double.parseDouble(stringPurchasePrice);
        String stringPayment = downPaymentTextField.getText();
        double downPayment = Double.parseDouble(stringPayment);
        double loanAmount = purchasePrice - downPayment;
        loanAmountTextField.setText(getYears.format(loanAmount));

//    //MyCode   ***********************************************
//        @FXML
//        void calculateButtonPressed() {
//            String stringPurchasePrice = purchasePriceTextField.getText();
//            BigDecimal purchasePrice = new BigDecimal(stringPurchasePrice);
//            String stringPayment = downPaymentTextField.getText();
//            BigDecimal downPayment = new BigDecimal(stringPayment);
//            BigDecimal loanAmount = purchasePrice.subtract(downPayment);
//            loanAmountTextField.setText(getYears.format(loanAmount));
//        // Endddddddd
//
        String stringYearLabel = yearLabel.getText();
        double getYearLabel = Double.parseDouble(stringYearLabel);
        double paymentNumber = getYearLabel *12;
        String userRate = interestRateTextField.getText();
        double Rate =  Double.parseDouble(userRate);
        double ratePerMonth = (Rate/100)/12;
        int roundedNumberOfPayments= (int)Math.round(paymentNumber);
        double term = 1 + ratePerMonth;
        double monthlyPayment = (ratePerMonth*loanAmount*pow(term,roundedNumberOfPayments))/(pow(term,roundedNumberOfPayments)-1);
        totalTextField.setText(getYears.format(monthlyPayment));
    }
}

/*
* package sample;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
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
                    yearLabel.setText(yearcool.format(years));
                }
        );
    }

    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    //private static final NumberFormat percent = NumberFormat.getPercentInstance();
    private static final NumberFormat year = NumberFormat.getIntegerInstance();

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
    void calculateButtonPressed() {
        String stringPurchasePrice = purchasePriceTextField.getText();
        BigDecimal purchasePrice = BigDecimal.valueof(stringPurchasePrice);
        String stringPayment = downPaymentTextField.getText();
        BigDecimal downPayment = BigDecimal.valueof(stringPayment);
        BigDecimal loanAmount = purchasePrice - downPayment;
        loanAmountTextField.setText(getYears.format(loanAmount));


         String stringYearLabel = yearLabel.getText();
       BigDecimal getYearLabel = new BigDecimal(stringYearLabel);
       BigDecimal paymentNumber =  getYearLabel.multiply(twelve);
       String userRate = interestRateTextField.getText();
       BigDecimal Rate =  new BigDecimal(userRate);
       BigDecimal ratePerMonthEx = Rate.divide(hundred);
       BigDecimal ratePerMonth = ratePerMonthEx.divide(twelve);

        String hjhj = yearLabel.getText();
       int hjhjfake = Int.parseInt(hjhj);

       int roundedNumberOfPayments= (int)Math.round(paymentNumber);
       double term = 1 + ratePerMonth;
       double monthlyPayment = (ratePerMonth*loanAmount*pow(term,roundedNumberOfPayments))/(pow(term,roundedNumberOfPayments)-1);
       totalTextField.setText(getYears.format(monthlyPayment));


        BigDecimal slidervalue = new BigDecimal(yearSlider.getValue());
        BigDecimal n = slidervalue.multiply(twelve);
        //Change n from bigDecimal to integer because pow only takes integer
        int kazi = n.intValue();
        String qw = String.valueOf(kazi);



        //VVVVVVVVVVV
        BigDecimal purchase_price = new BigDecimal(purchasePriceTextField.getText());
        BigDecimal down_payment = new BigDecimal(downPaymentTextField.getText());
        BigDecimal loan_amount = purchase_price.subtract(down_payment);
        loanAmountTextField.setText(currency.format(loan_amount));
        BigDecimal rate = new BigDecimal(interestRateTextField.getText());
        BigDecimal interest_rate = rate.divide(hundred);
        BigDecimal r = interest_rate.divide(twelve);

        //Numerator
        BigDecimal ex = one.add(r);
        BigDecimal exponent = ex.pow(kazi);
        BigDecimal numerator = loan_amount.multiply(exponent);
        BigDecimal numeratormain = numerator.multiply(r);

        //Denomator
        BigDecimal fx = one.add(r);
        BigDecimal expon2 = fx.pow(kazi);
        BigDecimal denomerator = expon2.subtract(one);

        BigDecimal whole = numeratormain.divide(denomerator);

        totalTextField.setText(year.format(kazi));
    }

}
*
* */