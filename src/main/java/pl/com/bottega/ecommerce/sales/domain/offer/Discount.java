package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Discount {
	private String discountCause;
	private BigDecimal discount;
	private Money money;

	public Discount(String discountCause,BigDecimal discount, Money money) {
		this.setDiscountCause(discountCause);
		this.setDiscount(discount);
		this.setMoney(money);
	}

	public String getDiscountCause() {
		return discountCause;
	}

	public void setDiscountCause(String discountCause) {
		this.discountCause = discountCause;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}
}