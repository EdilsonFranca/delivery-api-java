package br.com.delivery.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.delivery.app.Model.Dashboard;

public interface DashboardRepository  extends JpaRepository<Dashboard, Long>{

}
