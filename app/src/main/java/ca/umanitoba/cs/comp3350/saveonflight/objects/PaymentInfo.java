package ca.umanitoba.cs.comp3350.saveonflight.objects;

/**
 * PaymentInfo.java
 * <p>
 * Interface for the business logic of sorting Flights.
 *
 * @author Kenny Zhang
 */

public class PaymentInfo {
    private String cardNum;
    private String expiryDate;
    private String name;
    private String fullPhoneNum;
    private String address;
    private String province;
    private String country;
    private String postalCode;

    private PaymentInfo(String cardNum, String expiryDate, String name, String fullPhoneNum, String address,
                        String province, String country, String postalCode) {
        this.cardNum = cardNum;
        this.expiryDate = expiryDate;
        this.name = name;
        this.fullPhoneNum = fullPhoneNum;
        this.address = address;
        this.province = province;
        this.country = country;
        this.postalCode = postalCode;
    }

    public static final class PaymentInfoBuilder {
        private String cardNum;
        private String expiryDate;
        private String name;
        private String fullPhoneNum;
        private String address;
        private String province;
        private String country;
        private String postalCode;

        public PaymentInfoBuilder(String cardNum, String expiryDate) {
            this.cardNum = cardNum;
            this.expiryDate = expiryDate;
        }

        public PaymentInfoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PaymentInfoBuilder setPhoneNumber(String fullPhoneNum) {
            this.fullPhoneNum = fullPhoneNum;
            return this;
        }

        public PaymentInfoBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public PaymentInfoBuilder setProvince(String province) {
            this.province = province;
            return this;
        }

        public PaymentInfoBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public PaymentInfoBuilder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public PaymentInfo build() {
            return new PaymentInfo(cardNum, expiryDate, name, fullPhoneNum, address, province, country, postalCode);
        }
    }
}
