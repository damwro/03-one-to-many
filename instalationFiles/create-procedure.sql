USE `03-one-to-many`;
DROP procedure IF EXISTS `getPhoneNumberToDriver`;

DELIMITER $$
USE `03-one-to-many`$$
CREATE PROCEDURE `getPhoneNumberToDriver` ( IN line_number varchar(128))
BEGIN
select dd.phone_number from driver_detail dd 
join driver d on d.driver_detail_id = dd.id
join bus_line bl on bl.driver_id = d.id 
where bl.title = bus_title;

END$$

DELIMITER ;

