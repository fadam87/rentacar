package pti.sb_rentacar_mvc.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_rentacar_mvc.database.Database;

@Service
public class AppService {

	private Database db;
	@Autowired
	public AppService(Database db) {
		super();
		this.db = db;
	}
	
	
	
}
