package recipes.entity;

import java.math.BigDecimal;
import java.util.Objects;

import provided.entity.EntityBase;

public class Ingerdient extends EntityBase {
	private Integer ingerdientId;
	private Integer recipeId;
	private unit unit;
	private String ingerdientName;
	private String instruction;
	private Integer ingerdientOrder;
	private BigDecimal amount;
	
	/**
	 *  print like: ID=5: 1/4 cup carrots, thinly sliced.
	 */
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		
		b.append("ID=").append(ingerdientId).append(": ");
		b.append(toFraction(amount));
		
		if(Objects.nonNull(unit) && Objects.nonNull(unit.getUnitId())) {
			String singular = unit.getUnitNameSingular();
			String plural = unit.getUnitNamePlural();
			String word = amount.compareTo(BigDecimal.ONE) > 0 ? plural : singular;
			
			b.append(word).append(" ");
		}
		
		b.append(ingerdientName);
		
		if(Objects.nonNull(instruction)) {
			b.append(", ").append(instruction);
		}
		
		return b.toString()
;	}
	public Integer getIngerdientId() {
		return ingerdientId;
	}
	public void setIngerdientId(Integer ingerdientId) {
		this.ingerdientId = ingerdientId;
	}
	public Integer getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}
	public unit getUnit() {
		return unit;
	}
	public void setUnit(unit unit) {
		this.unit = unit;
	}
	public String getIngerdientName() {
		return ingerdientName;
	}
	public void setIngerdientName(String ingerdientName) {
		this.ingerdientName = ingerdientName;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public Integer getIngerdientOrder() {
		return ingerdientOrder;
	}
	public void setIngerdientOrder(Integer ingerdientOrder) {
		this.ingerdientOrder = ingerdientOrder;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	
	
}
