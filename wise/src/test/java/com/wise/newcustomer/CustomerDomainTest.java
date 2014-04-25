package com.wise.newcustomer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.wise.core.RepositoryContextHolder;
import com.wise.newcustomer.code.Address;
import com.wise.newcustomer.code.Annual;
import com.wise.newcustomer.code.Email;
import com.wise.newcustomer.code.Relation;
import com.wise.newcustomer.code.Sns;
import com.wise.newcustomer.privacy.Anniversary;
import com.wise.newcustomer.privacy.Contact;
import com.wise.newcustomer.privacy.Family;
import com.wise.newcustomer.privacy.Occupation;
import com.wise.newcustomer.privacy.PrivacyType;
import com.wise.note.Note;
import com.wise.note.mem.InMemoryNoteRepository;

public class CustomerDomainTest {

	/**
	 * 공통 Repository 설정
	 */
	@Before
	public void init() {
		RepositoryContextHolder.register(Note.class, new InMemoryNoteRepository());
	}
	
	@Test
	public void test_고객_기본정보() {
		CustomerBuilder builder = new CustomerBuilder();
		
		Customer customer = builder.name("엘사").gender(Gender.FEMALE).serial("SI800816").build();
		
		assertCustomer(customer);
	}
	
	@Test
	public void test_고객_상세정보() {
		CustomerBuilder builder = new CustomerBuilder();
		Customer customer = builder.name("엘사").gender(Gender.FEMALE).serial("SI800816").build();
		
		//가족
		customer.addPrivacy(new Family(Relation.sister, "0000.00.00", "안나"));
		
		//메일
		customer.addPrivacy(new Contact(Email.gmail, "elsa@gmail.com"));
		customer.addPrivacy(new Contact(Email.daum, "elsa@daum.net"));
		
		//주소
		customer.addPrivacy(new Contact(Address.home, "아렌델 왕궁"));
		
		//홈페이지
		customer.addPrivacy(new Contact("www.elsa.net"));
		
		//sns
		customer.addPrivacy(new Contact(Sns.facebook, "elsa"));
		
		//직업
		customer.addPrivacy(new Occupation("아렌델 왕국", "여왕"));
		
		//기념일
		customer.addPrivacy(new Anniversary(Annual.founding, "2013-11-01"));
		
		assertCustomer(customer);
		
		assertThat(customer.findPrivacy(PrivacyType.family).size(), is(1));
		assertThat(customer.findPrivacy(PrivacyType.email).size(), is(2));
		assertThat(customer.findPrivacy(PrivacyType.address).size(), is(1));
		assertThat(customer.findPrivacy(PrivacyType.homepage).size(), is(1));
		assertThat(customer.findPrivacy(PrivacyType.sns).size(), is(1));
		assertThat(customer.findPrivacy(PrivacyType.occupation).size(), is(1));
		assertThat(customer.findPrivacy(PrivacyType.anniversary).size(), is(1));
	}
	
	@Test
	public void test_회원_추천하기() {		
		throw new RuntimeException();
	}
	
	@Test
	public void test_추천_회원_목록() {
		throw new RuntimeException();
	}
	
	@Test
	public void test_고객_메모_등록() {
		CustomerBuilder builder = new CustomerBuilder();
		Customer customer = builder.name("엘사").gender(Gender.FEMALE).serial("SI800816").build();
		
		customer.writeMemo("겨울왕국 짱 재밌어!!!!");
		
		Note memo = customer.getMemo().get(0);
		
		assertNotNull(memo.getNoteId());
		assertNotNull(memo.getWriteTime());
		assertThat(memo.getNote(), is("겨울왕국 짱 재밌어!!!!"));
	}
	
	private void assertCustomer(Customer customer) {
		assertNotNull(customer);
		assertNotNull(customer.getCustomerId());
		assertThat(customer.getName(), is("엘사"));
		assertThat(customer.getGender(), is(Gender.FEMALE));
		assertThat(customer.getSerial(), is("SI800816"));		
	}
}
