package aula20200825.abstractRepository;

public class AbstractEntity {
	private Long id;
	
	
	public AbstractEntity() {
	}
	
	public AbstractEntity(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
