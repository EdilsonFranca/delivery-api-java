package br.com.delivery.app.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.delivery.app.Model.Dashboard;
import br.com.delivery.app.Repository.DashboardRepository;

@RestController
@CrossOrigin(origins="*",maxAge = 3600)
@RequestMapping({"/dashboard"})
public class DashboardController {

	@Autowired
	private  DashboardRepository  dashboardCategory;
	
	@RequestMapping
	public ResponseEntity<?> dashboard(){
		Optional<Dashboard> dashboard = dashboardCategory.findById((long) 1);
		return ResponseEntity.ok(dashboard);
	}
	
	@PutMapping
	  public ResponseEntity<Dashboard> update(@RequestBody Dashboard dashboard){
	    return dashboardCategory.findById((long) 1)
	        .map(record -> {
	            record.setDelivery_is_open(dashboard.isDelivery_is_open());
	            Dashboard updated = dashboardCategory.save(record);
	            return ResponseEntity.ok().body(updated);
	        }).orElse(ResponseEntity.notFound().build());
	  }
	
}
