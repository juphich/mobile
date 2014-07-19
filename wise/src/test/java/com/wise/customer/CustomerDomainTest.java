package com.wise.customer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.wise.category.Category;
import com.wise.category.CategoryType;
import com.wise.core.GenericQuery;
import com.wise.core.Query;
import com.wise.core.RepositoryContextHolder;
import com.wise.customer.Customer;
import com.wise.customer.CustomerBuilder;
import com.wise.customer.Gender;
import com.wise.customer.code.Address;
import com.wise.customer.code.Annual;
import com.wise.customer.code.Email;
import com.wise.customer.code.Relation;
import com.wise.customer.code.Sns;
import com.wise.customer.mock.MockCustomerRepository;
import com.wise.customer.privacy.Anniversary;
import com.wise.customer.privacy.Contact;
import com.wise.customer.privacy.Family;
import com.wise.customer.privacy.Occupation;
import com.wise.customer.privacy.PrivacyType;
import com.wise.note.Note;
import com.wise.note.mock.MockNoteRepository;
import com.wise.volume.Volume;
import com.wise.volume.mock.MockVolumeRepository;

public class CustomerDomainTest {

	/**
	 * 공통 Repository 설정
	 */
	@Before
	public void init() {
		RepositoryContextHolder.register(Customer.class, new MockCustomerRepository());
		RepositoryContextHolder.register(Note.class, new MockNoteRepository());
		RepositoryContextHolder.register(Volume.class, new MockVolumeRepository());
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
		Customer elsa = new CustomerBuilder().name("엘사").gender(Gender.FEMALE).build();
		Customer anna = new CustomerBuilder().name("안나").gender(Gender.FEMALE).build();
		Customer olaf = new CustomerBuilder().name("올라프").gender(Gender.UNKNOWN).build();
		
		anna.recommend(elsa);
		olaf.recommend(elsa);
		
		assertThat(anna.getRecommend(), is(elsa));  // 안나의 추천인
		assertThat(olaf.getRecommend(), is(elsa));  // 올라프의 추천인
		
		// 엘사의 볼륨 정보
		Volume volume = RepositoryContextHolder.repository(Volume.class).find(elsa.getCustomerId());
		assertThat(volume.getMaster(), is(elsa.getCustomerId()));
		assertThat(volume.getSize(), is(2));
	}
	
	@Test
	public void test_회원_분류_등록() {
		Customer elsa = new CustomerBuilder().name("엘사").gender(Gender.FEMALE).build();
		
		elsa.addCategory(new Category("partner", "파트너", CategoryType.customer));
		elsa.addCategory(new Category("friend", "친구", CategoryType.customer));
		
		assertThat(elsa.getCategories().size(), is(2));
	}
	
	@Test
	public void test_회원_분류별_조회() {
		Category partner = new Category("partner", "파트너", CategoryType.customer);
		Category friend = new Category("friend", "친구", CategoryType.customer);
		
		RepositoryContextHolder.repository(Customer.class).save(
				new CustomerBuilder().name("엘사").category(friend).build()
			);
		RepositoryContextHolder.repository(Customer.class).save(
				new CustomerBuilder().name("안나").category(friend).build()
			);
		RepositoryContextHolder.repository(Customer.class).save(
				new CustomerBuilder().name("올라프").category(friend).category(partner).build()
			);
		
		
		Query query = new GenericQuery();
		query.q("category", friend);
		
		List<Customer> friends = RepositoryContextHolder.repository(Customer.class).search(query);
		assertThat(friends.size(), is(3));
		
		query.q("category", partner);
		List<Customer> partners = RepositoryContextHolder.repository(Customer.class).search(query);
		assertThat(partners.size(), is(1));
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
