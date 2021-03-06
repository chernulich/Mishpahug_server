package Application.entities;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import Application.entities.values.LogsDataValue;
import Application.entities.values.PictureValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
//@Getter @Setter @AllArgsConstructor @NoArgsConstructor
//@EqualsAndHashCode(of = "id")
@ToString(exclude = { "eventItemsOwner", "eventItemsGuest", "pictureItems", "feedBackEntities" })
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nickname")
	private String nickname; // TODO: unique nickname;

    @Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;


	@ElementCollection
	@CollectionTable
	private List<LogsDataValue> logs;

	@Column(name = "phonenumber")
	private String phoneNumber;

	@Column(name = "email")
	private String eMail;

	@Enumerated(EnumType.STRING)
    @Column(name = "role")
	private UserRole role;

    @JsonManagedReference
    @Column(name = "feedbacks")
	private HashMap<Integer, FeedBackEntity> feedBacks;

	@OneToOne(mappedBy = "userEntity") // Address of user
	@JsonManagedReference
	private AddressEntity addressEntity;

	@OneToMany(mappedBy = "userEntityOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // User owner of events
	@Column(unique = true)
	@JsonManagedReference
	private Set<EventEntity> eventItemsOwner = new HashSet<>();

	@ManyToMany(mappedBy = "userItemsGuestsOfEvents") // User a guest in events
	@JsonManagedReference
	private Set<EventEntity> eventItemsGuest = new HashSet<>();

	@ElementCollection
	@CollectionTable
    @Column(name = "pictures")
	private Set<PictureValue> pictureItems = new HashSet<>();

	@OneToMany(mappedBy = "userItem", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<FeedBackEntity> feedBackEntities = new HashSet<>();

	public enum UserRole {
		ADMIN, AUTHORISED, SUSPENDED,
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<LogsDataValue> getLogs() {
		return logs;
	}

	public void setLogs(List<LogsDataValue> logs) {
		this.logs = logs;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public HashMap<Integer, FeedBackEntity> getFeedBacks() {
		return feedBacks;
	}

	public void setFeedBacks(HashMap<Integer, FeedBackEntity> feedBacks) {
		this.feedBacks = feedBacks;
	}

	public AddressEntity getAddressEntity() {
		return addressEntity;
	}

	public void setAddressEntity(AddressEntity addressEntity) {
		this.addressEntity = addressEntity;
	}

	public Set<EventEntity> getEventItemsOwner() {
		return eventItemsOwner;
	}

	public void setEventItemsOwner(Set<EventEntity> eventItemsOwner) {
		this.eventItemsOwner = eventItemsOwner;
	}

	public Set<EventEntity> getEventItemsGuest() {
		return eventItemsGuest;
	}

	public void setEventItemsGuest(Set<EventEntity> eventItemsGuest) {
		this.eventItemsGuest = eventItemsGuest;
	}

	public Set<PictureValue> getPictureItems() {
		return pictureItems;
	}

	public void setPictureItems(Set<PictureValue> pictureItems) {
		this.pictureItems = pictureItems;
	}

	public Set<FeedBackEntity> getFeedBackEntities() {
		return feedBackEntities;
	}

	public void setFeedBackEntities(Set<FeedBackEntity> feedBackEntities) {
		this.feedBackEntities = feedBackEntities;
	}

	public UserEntity() {
	}

	public UserEntity(String nickname, String firstName, String lastName, List<LogsDataValue> logs, String phoneNumber, String eMail, UserRole role, HashMap<Integer, FeedBackEntity> feedBacks, AddressEntity addressEntity, Set<EventEntity> eventItemsOwner, Set<EventEntity> eventItemsGuest, Set<PictureValue> pictureItems, Set<FeedBackEntity> feedBackEntities) {
		this.nickname = nickname;
		this.firstName = firstName;
		this.lastName = lastName;
		this.logs = logs;
		this.phoneNumber = phoneNumber;
		this.eMail = eMail;
		this.role = role;
		this.feedBacks = feedBacks;
		this.addressEntity = addressEntity;
		this.eventItemsOwner = eventItemsOwner;
		this.eventItemsGuest = eventItemsGuest;
		this.pictureItems = pictureItems;
		this.feedBackEntities = feedBackEntities;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserEntity that = (UserEntity) o;
		return id.equals(that.id) &&
				nickname.equals(that.nickname) &&
				firstName.equals(that.firstName) &&
				lastName.equals(that.lastName) &&
				logs.equals(that.logs) &&
				phoneNumber.equals(that.phoneNumber) &&
				eMail.equals(that.eMail) &&
				role == that.role &&
				feedBacks.equals(that.feedBacks) &&
				addressEntity.equals(that.addressEntity) &&
				eventItemsOwner.equals(that.eventItemsOwner) &&
				eventItemsGuest.equals(that.eventItemsGuest) &&
				pictureItems.equals(that.pictureItems) &&
				feedBackEntities.equals(that.feedBackEntities);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nickname, firstName, lastName, logs, phoneNumber, eMail, role, feedBacks, addressEntity, eventItemsOwner, eventItemsGuest, pictureItems, feedBackEntities);
	}
}
