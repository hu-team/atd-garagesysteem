INSERT INTO `artikel` (`code`, `naam`, `aantal`, `prijs`) VALUES
('ACV-1-0_5L', 'Airco vloeistof 0.5L', 25, 80),
('FRD-ASKR-01', 'Ford Askeering', 1, 35),
('FRD-FNTB-001', 'Ford Frontbalk', 2, 175),
('FRD-POLYV-1', 'Ford Poly V', 5, 125);

INSERT INTO `klant` (`gebruikersnaam`, `wachtwoord`, `laatste_bezoek`, `email`, `naam`, `adres`, `postcode`, `woonplaats`, `telefoonnummer`) VALUES
('e.oegema', '42582b095c8e22977c5976edef161ad6052578d2af5398e9f52a4aae620b3bb23bf20c537f0db190919b171bdc8112fb0cdd27f4f9f9bda610b4327b83885c88', NULL, 'ErtanOegema@teleworm.us', 'Ertan Oegema', 'Maaiklinkstraat 186', '4438AN', 'Driewegen', '06-41230923'),
('josine.bolle', '67d88d4b92e6e7c7fb54bae700d6e3d3bbf3513cf4b7ad205dd19d8d4ada81b474e3cb7b562699f3369413fb5aa47475a2f921213f03978cecb3c2237f3bd695', NULL, 'JosineBolle@rhyta.com', 'Josine Bolle', 'Broekestraat 98', '5912PC', 'Venlo', '06-79628040'),
('melinda.blijderveen', '48e8b4bf8cbb0ebeccaed0acc8996e379af6c0f3d9107dcb06e35c2cf4a985084f59986c079c2fc52eca849d08120449e3aea09274743ed84ad0ea8d636e611f', NULL, 'MelindavanBlijderveen@teleworm.us', 'Melinda van Blijderveen', 'Glindhorst 183', '6714KE', 'Ede', '06-27322429');


INSERT INTO `auto` (`autoid`, `merk`, `model`, `bouwjaar`, `kenteken`, `laatste_beurt`, `klant`) VALUES
(1, 'ASTON-MARTIN', 'RAPIDE', 2012, '43LSF3', NULL, 'e.oegema'),
(2, 'MALAGUTI', 'CENTRO', 2007, 'D414GG', NULL, 'e.oegema'),
(3, 'PEUGEOT', '207', 2006, '01RDX3', NULL, 'josine.bolle'),
(4, 'KIA', 'PICANTO', 2011, '88GBG1', NULL, 'josine.bolle'),
(5, 'TOYOTA', 'TOYOTA PRIUS', 2009, '80HNZ4', NULL, 'melinda.blijderveen');


INSERT INTO `monteur` (`gebruikersnaam`, `wachtwoord`, `naam`, `salarisnummer`) VALUES
('aarnoudboekema', '44c4cbc5f8d33631f691917177a07937539ff6277216f844a78df6d27f7ba79a8884757835cffb7da2589036ce9909fa23b10fb2b8b2373b4a9b8fa171889724', 'Aarnoud Boekema', 5002),
('anniekevangroningen', '96f86d1ae8fa33c308a83f4cb83db41c829258312a1b3936eecbd5b0fe25b8784015c5374b50be95521beb8456d8a933488fad33c427ac804919ec91e1977a15', 'Annieke van Groningen', 5001);
