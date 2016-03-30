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

	// product
	private String productId;

	private BigDecimal productPrice;

	private String productName;

	private Date productSnapshotDate;

	private String productType;

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
		this.productId = productId;
		this.productPrice = productPrice;
		this.productName = productName;
		this.productSnapshotDate = productSnapshotDate;
		this.productType = productType;

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
		return productId;
	}
	
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public Date getProductSnapshotDate() {
		return productSnapshotDate;
	}
	
	public String getProductType() {
		return productType;
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
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((productPrice == null) ? 0 : productPrice.hashCode());
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
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
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (productPrice == null) {
			if (other.productPrice != null)
				return false;
		} else if (!productPrice.equals(other.productPrice))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productType != other.productType)
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
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (productPrice == null) {
			if (other.productPrice != null)
				return false;
		} else if (!productPrice.equals(other.productPrice))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productType != other.productType)
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
