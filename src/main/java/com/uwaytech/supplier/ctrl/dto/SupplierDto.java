package com.uwaytech.supplier.ctrl.dto;

		import com.uwaytech.supplier.domain.Supplier;

		import java.util.ArrayList;
		import java.util.List;

/**
 * Created by zeng on 2016/7/12.
 */
public class SupplierDto {

	private String supplierId;
	private String name;
	private String account;
	private String contacts;
	private String phone;
	private String address;

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static List<SupplierDto> toSupplierDtoList(List<Supplier> result) {
		List<SupplierDto> list = new ArrayList<>();
		for (Supplier supplier : result) {
			SupplierDto dto = new SupplierDto();
			dto.setSupplierId(supplier.getId().toString());
			dto.setAccount(supplier.getAccount());
			dto.setContacts(supplier.getContacts());
			dto.setName(supplier.getName());
			dto.setAddress(supplier.getAddress());
			dto.setPhone(supplier.getPhone());
			list.add(dto);
		}
		return list;
	}
}
