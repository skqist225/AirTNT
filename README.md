# AirTNT

Air TNT web project inspired by Airbnb

@AboutProject
class ProjectVersionInfo {

public static final int Java = 11;

public static final String SpringBoot = "2.54";

public static final String Maven = "3.8";

public static final float TomCat = 9.0;
}

///Change countries table character set
ALTER TABLE airtnt.countries CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

///Drop foreign key check
SET FOREIGN_KEY_CHECKS = 0;

///List of MIMETYPE
https://www.iana.org/assignments/media-types/media-types.xhtml#image

//INSERT INTO `airtnt`.`reviews` (`created_at`, `comment`, `final_rating`, `accuracy_rating`, `checkin_rating`, `cleanliness_rating`, `contact_rating`, `location_rating`, `value_rating`, `booking_id`, `user_id`, `room_id`) VALUES (NOW(), 'hay lam', 4, 4, 4, 4, 4, 4, 4, 1, 17, 1);

//INSERT INTO `airtnt`.`bookings` (`created_at`, `booking_date`, `checkin_date`, `checkout_date`, `is_complete`, `is_refund`, `number_of_days`, `price_per_day`, `refund_paid`, `site_fee`, `total_fee`, `customer_id`, `room_id`) VALUES ('2021-12-04 00:00:00.000000', '2021-12-30 21:53:25.158439', '2021-12-01 00:00:00.000000', '2021-12-04 00:00:00.000000', 1, 0, 43, 645000.00, 0.00, 129000.00, 2580000.00, 17, 4);
