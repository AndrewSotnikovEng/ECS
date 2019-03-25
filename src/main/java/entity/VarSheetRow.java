package entity;

public class VarSheetRow {

	// component, description, requirements
	private String comp;
	private String desc;
	private String req;

	public VarSheetRow(String comp, String desc, String req) {

		this.setComponent(comp);
		this.setDescription(desc);
		this.setRequirements(req);

	}

	public String getComponent() {
		return comp;
	}

	public void setComponent(String component) {
		this.comp = component;
	}

	public String getDescription() {
		return desc;
	}

	public void setDescription(String description) {
		this.desc = description;
	}

	public String getRequirements() {
		return req;
	}

	public void setRequirements(String requirements) {
		this.req = requirements;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String output = " Comp: " + this.getComponent() + "  Desc: " + this.getDescription() + "  Req: "
				+ this.getRequirements();

		return output;
	}

}
