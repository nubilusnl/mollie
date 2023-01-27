package nl.nubilus.mollie.api.links;

public class MollieLinks {
    private MollieLink self;
    private MollieLink checkout;
    private MollieLink dashboard;
    private MollieLink customer;
    private MollieLink documentation;

    public MollieLink getSelf() {
        return self;
    }

    public void setSelf(MollieLink self) {
        this.self = self;
    }

    public MollieLink getCheckout() {
        return checkout;
    }

    public void setCheckout(MollieLink checkout) {
        this.checkout = checkout;
    }

    public MollieLink getDashboard() {
        return dashboard;
    }

    public void setDashboard(MollieLink dashboard) {
        this.dashboard = dashboard;
    }

    public MollieLink getCustomer() {
        return customer;
    }

    public void setCustomer(MollieLink customer) {
        this.customer = customer;
    }

    public MollieLink getDocumentation() {
        return documentation;
    }

    public void setDocumentation(MollieLink documentation) {
        this.documentation = documentation;
    }
}
