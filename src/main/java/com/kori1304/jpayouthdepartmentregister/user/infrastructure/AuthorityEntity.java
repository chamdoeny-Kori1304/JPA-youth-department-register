package com.kori1304.jpayouthdepartmentregister.user.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Table(name = "authority")
@Builder(toBuilder = true)
public class AuthorityEntity {


	@Id
	private long id;
	
	private String name;

	private String desc;


}





