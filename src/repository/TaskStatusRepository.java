package repository;

public enum TaskStatusRepository {
	TO_DO("To do"),
	IN_PROGRESS("In progress"),
	DONE("Done");
	
	private String status;
	
	private TaskStatusRepository(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
