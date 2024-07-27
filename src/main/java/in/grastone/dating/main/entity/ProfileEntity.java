package in.grastone.dating.main.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a user profile in a dating application with both standard and premium features.
 * This class uses Lombok annotations to reduce boilerplate code.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "profiles")
@Table(name = "profiles")
public class ProfileEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key with auto-increment

    // Basic profile details
    private String username;                // Unique identifier for the user
    private String fullName;                // Full name of the user
    private int age;                        // Age of the user
    private String location;                // Geographic location of the user
    private String occupation;              // Job or career information
    private String education;              // Educational background
    private String relationshipStatus;     // Current relationship status
    private String sexualOrientation;       // Sexual orientation of the user

//    @OneToMany(mappedBy = "profile")
//    private List<InterestProfileMappingEntity> interests = new ArrayList<>();  // List of personal interests

//    @OneToMany(mappedBy = "profile")
//    private List<FavoriteActivityProfileMappingEntity> favoriteActivities = new ArrayList<>();  // List of favorite activities

    private boolean isSmoker;               // Smoking habits
    private boolean isDrinker;              // Drinking habits
    private boolean isVegetarian;           // Vegetarian status
    private String goals;                   // Personal goals
    private String values;                  // Core values
    private boolean hasPets;                // Whether the user has pets
    
//    @OneToMany(mappedBy = "profile")
//    private List<LanguagesProfileMappingEntity> languages = new ArrayList<>();  // Languages spoken
    private String religiousBeliefs;        // Religious beliefs
    private String lookingFor;              // What the user is seeking in a partner
    private String astrologicalSign;        // Astrological sign
    
//    @OneToMany(mappedBy = "profile")
//    private List<PhotosProfileMappingEntity> photos = new ArrayList<>();  // List of profile photos

    private boolean isPremiumMember;                // Indicates if the user is a premium member
    private String premiumMemberSince;             // Date when the premium membership started
    private String premiumFeatures;                // Features available to premium members
    private String personalizedMessage;            // Special message or introduction from the user
    private String favoriteTravelDestinations;     // Favorite travel destinations
    
}
