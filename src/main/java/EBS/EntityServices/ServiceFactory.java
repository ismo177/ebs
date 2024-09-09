
package EBS.EntityServices;

import EBS.EntityServices.Bill.BillService;
import EBS.EntityServices.Customer.CustomerService;
import EBS.EntityServices.Tax.TaxService;
import EBS.EntityServices.User.UserService;

public enum ServiceFactory {
    USER_SERVICE(new UserService()), CUSTOMER_SERVICE(new CustomerService()),
    TAX_SERVICE(new TaxService()), BILL_SERVICE(new BillService());


    private UserService userService;
    private CustomerService customerService;
    private TaxService taxService;
    private BillService billService;


    ServiceFactory(CustomerService customerService) {
        this.customerService = customerService;

    }

    ServiceFactory(TaxService taxService) {
        this.taxService = taxService;
    }

    ServiceFactory(BillService billService) {
        this.billService = billService;
    }

    ServiceFactory(UserService userService) {
        this.userService = userService;
    }


    public UserService getUserService() {
        return userService;
    }
    public CustomerService getCustomerService() {
        return customerService;
    }

    public TaxService getTaxService() {
        return taxService;
    }

    public BillService getBillService() {
        return billService;
    }

   /*
    public static AbstractService getService(String type) {

        if (type.equalsIgnoreCase("BillService")) {
            return new BillService();
        } else if (type.equalsIgnoreCase("CustomerService")) {
            return new CustomerService();
        } else if (type.equalsIgnoreCase("TaxService")) {
            return new TaxService();
        } else if (type.equalsIgnoreCase("UserService")) {
            return new UserService();
        }
            return null;
    }

    */
}
