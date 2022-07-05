package com.prata.web_services_new.entities.enums;

public enum OrderStatus {

	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELAD(5);
	
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
		
	}
	
	public int getCode() {
		return code;
	}
	
	/* Para percorrer a lista enumerada e retornar qual é o código executa-se o código a seguir que percorre a lista de enum
	 * Isso é importante para eveitar que um programador que desconheça, inclua um outro enum na lista e bagunçe todo programa
	 * Será public Static pois não será instanciado
	 */
	public static OrderStatus valueOf(int code) {
		for (OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Código Inválido");
	}

}
