/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;

public class OfferItem {

	private Product Product;
	private int quantity;
	private Money Money;
	private Discount Discount;

	public OfferItem(String productId, BigDecimal productPrice, String productName,
			Date productSnapshotDate, String productType, int quantity) {
		this(productId, productPrice, productName, productSnapshotDate, productType, quantity, null, null);
	}

	public OfferItem(String productId, BigDecimal productPrice, String productName,
			Date productSnapshotDate, String productType, int quantity,
			BigDecimal discount, String discountCause) {
		this.Product.setProductId(productId);
		this.Product.setProductPrice(productPrice);
		this.Product.setProductName(productName);
		this.Product.setProductSnapshotDate(productSnapshotDate);
		this.Product.setProductType(productType);

		this.quantity = quantity;
		this.Discount.setDiscount(discount);
		this.Discount.setDiscountCause(discountCause);

		BigDecimal discountValue = new BigDecimal(0);
		if (discount != null)
			discountValue = discountValue.subtract(discount);

		this.Money.setTotalCost(productPrice
				.multiply(new BigDecimal(quantity)).subtract(discountValue));
	}

	public String getProductId() {
		return Product.getProductId();
	}
	
	public BigDecimal getProductPrice() {
		return Product.getProductPrice();
	}
	
	public String getProductName() {
		return Product.getProductName();
	}
	
	public Date getProductSnapshotDate() {
		return Product.getProductSnapshotDate();
	}
	
	public String getProductType() {
		return Product.getProductType();
	}

	public BigDecimal getTotalCost() {
		return Money.getTotalCost();
	}

	public String getTotalCostCurrency() {
		return Money.getCurrency();
	}

	public BigDecimal getDiscount() {
		return Discount.getDiscount();
	}

	public String getDiscountCause() {
		return Discount.getDiscountCause();
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((Discount.getDiscount() == null) ? 0 : Discount.getDiscount().hashCode());
		result = prime * result + ((Product.getProductName() == null) ? 0 : Product.getProductName().hashCode());
		result = prime * result + ((Product.getProductPrice() == null) ? 0 : Product.getProductPrice().hashCode());
		result = prime * result
				+ ((Product.getProductId() == null) ? 0 : Product.getProductId().hashCode());
		result = prime * result + ((Product.getProductType() == null) ? 0 : Product.getProductType().hashCode());
		result = prime * result + quantity;
		result = prime * result
				+ ((Money.getTotalCost() == null) ? 0 : Money.getTotalCost().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfferItem other = (OfferItem) obj;
		if (Discount.getDiscount() == null) {
			if (other.Discount.getDiscount() != null)
				return false;
		} else if (!Discount.getDiscount().equals(other.Discount.getDiscount()))
			return false;
		if (Product.getProductName() == null) {
			if (other.Product.getProductName() != null)
				return false;
		} else if (!Product.getProductName().equals(other.Product.getProductName()))
			return false;
		if (Product.getProductPrice() == null) {
			if (other.Product.getProductPrice() != null)
				return false;
		} else if (!Product.getProductPrice().equals(other.Product.getProductPrice()))
			return false;
		if (Product.getProductId() == null) {
			if (other.Product.getProductId() != null)
				return false;
		} else if (!Product.getProductId().equals(other.Product.getProductId()))
			return false;
		if (Product.getProductType() != other.Product.getProductType())
			return false;
		if (quantity != other.quantity)
			return false;
		if (Money.getTotalCost() == null) {
			if (other.Money.getTotalCost() != null)
				return false;
		} else if (!Money.getTotalCost().equals(other.Money.getTotalCost()))
			return false;
		return true;
	}

	/**
	 * 
	 * @param item
	 * @param delta
	 *            acceptable percentage difference
	 * @return
	 */
	public boolean sameAs(OfferItem other, double delta) {
		if (Product.getProductName() == null) {
			if (other.Product.getProductName() != null)
				return false;
		} else if (!Product.getProductName().equals(other.Product.getProductName()))
			return false;
		if (Product.getProductPrice() == null) {
			if (other.Product.getProductPrice() != null)
				return false;
		} else if (!Product.getProductPrice().equals(other.Product.getProductPrice()))
			return false;
		if (Product.getProductId() == null) {
			if (other.Product.getProductId() != null)
				return false;
		} else if (!Product.getProductId().equals(other.Product.getProductId()))
			return false;
		if (Product.getProductType() != other.Product.getProductType())
			return false;

		if (quantity != other.quantity)
			return false;

		BigDecimal max, min;
		if (Money.getTotalCost().compareTo(other.Money.getTotalCost()) > 0) {
			max = Money.getTotalCost();
			min = other.Money.getTotalCost();
		} else {
			max = other.Money.getTotalCost();
			min = Money.getTotalCost();
		}

		BigDecimal difference = max.subtract(min);
		BigDecimal acceptableDelta = max.multiply(new BigDecimal(delta / 100));

		return acceptableDelta.compareTo(difference) > 0;
	}

}
