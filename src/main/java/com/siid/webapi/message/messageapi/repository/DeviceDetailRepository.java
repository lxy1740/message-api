package com.siid.webapi.message.messageapi.repository;

import com.siid.webapi.message.messageapi.domain.DeviceDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceDetailRepository extends JpaRepository<DeviceDetailEntity, Integer> {
}
