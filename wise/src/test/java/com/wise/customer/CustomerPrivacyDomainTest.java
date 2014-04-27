package com.wise.customer;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import com.wise.customer.code.Annual;
import com.wise.customer.code.ContactKind;
import com.wise.customer.code.Email;
import com.wise.customer.code.Relation;
import com.wise.customer.code.Sns;
import com.wise.customer.privacy.Anniversary;
import com.wise.customer.privacy.Contact;
import com.wise.customer.privacy.Family;
import com.wise.customer.privacy.Occupation;
import com.wise.customer.privacy.PrivacyType;

public class CustomerPrivacyDomainTest {

	@Test
	public void test_가족_정보() {
		Family sister = new Family();
		sister.setBirth("2001.09.01");
		sister.setName("안나");
		sister.setRelation(Relation.sister);
		
		assertThat(sister.type(), is(PrivacyType.family));
		assertThat(sister.getName(), is("안나"));
		assertThat(sister.getBirth(), is("2001.09.01"));
		assertThat(sister.getRelation(), is(Relation.sister));
	}
	
	@Test
	public void test_연락처_정보() {
		Contact homepage = new Contact("www.elsa.net");
		assertThat(homepage.type(), is(PrivacyType.homepage));
		assertThat(homepage.getContact(), is("www.elsa.net"));
		
		Contact email = new Contact(Email.gmail);
		
		email.setContact(Email.gmail.address("elsa"));
		
		assertThat(email.type(), is(PrivacyType.email));
		assertThat(email.getKind(), is((ContactKind)Email.gmail));
		assertThat(email.getContact(), is("elsa@gmail.com"));
		
		Contact sns = new Contact(Sns.twitter);
		sns.setKind(Sns.twitter);
		sns.setContact("@elsa");
		
		assertThat(sns.type(), is(PrivacyType.sns));
		assertThat(sns.getKind(), is((ContactKind)Sns.twitter));
		assertThat(sns.getContact(), is("@elsa"));
	}
	
	@Test
	public void test_부가정보() {
		Occupation work = new Occupation("아렌델 왕국", "여왕");
		
		assertThat(work.type(), is(PrivacyType.occupation));
		assertThat(work.getCompany(), is("아렌델 왕국"));
		assertThat(work.getDuty(), is("여왕"));
		
		Anniversary founding = new Anniversary(Annual.founding, "2013-11-01");
		
		assertThat(founding.type(), is(PrivacyType.anniversary));
		assertThat(founding.getAnnual(), is(Annual.founding));
		assertThat(founding.getDate(), is("2013-11-01"));
	}
}
