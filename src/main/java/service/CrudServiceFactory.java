
package service;

import service.bill.BillServiceFactory;
import service.Customer.CustomerServiceFactory;
import service.Tax.TaxServiceFactory;
import service.User.UserServiceFactory;

public enum CrudServiceFactory {
    USER_SERVICE(new UserServiceFactory()), CUSTOMER_SERVICE(new CustomerServiceFactory()),
    TAX_SERVICE(new TaxServiceFactory()), BILL_SERVICE(new BillServiceFactory());


    private UserServiceFactory userServiceFactory;
    private CustomerServiceFactory customerServiceFactory;
    private TaxServiceFactory taxServiceFactory;
    private BillServiceFactory billServiceFactory;


    CrudServiceFactory(CustomerServiceFactory customerServiceFactory) {
        this.customerServiceFactory = customerServiceFactory;

    }

    CrudServiceFactory(TaxServiceFactory taxServiceFactory) {
        this.taxServiceFactory = taxServiceFactory;
    }

    CrudServiceFactory(BillServiceFactory billServiceFactory) {
        this.billServiceFactory = billServiceFactory;
    }

    CrudServiceFactory(UserServiceFactory userServiceFactory) {
        this.userServiceFactory = userServiceFactory;
    }


    public UserServiceFactory getUserService() {
        return userServiceFactory;
    }
    public CustomerServiceFactory getCustomerService() {
        return customerServiceFactory;
    }

    public TaxServiceFactory getTaxService() {
        return taxServiceFactory;
    }

    public BillServiceFactory getBillService() {
        return billServiceFactory;
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
