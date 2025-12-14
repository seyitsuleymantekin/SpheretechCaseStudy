Metot,URL,Açıklama
GET,/api/airlines/all,Tüm Havayollarını Listele
GET,/api/airlines/search?airlineName={Name},Name ile Havayolu Ara
GET,/api/airports/all,Tüm Havaalanlarını Listele
GET,/api/airports/search/code?airportCode={Code},Code ile Havaalanı Ara (Örn: IST)
GET,/api/airports/search/name?airportName={Name},Name ile Havaalanı Ara
GET,/api/routes/search/code?dep={Code}&arr={Code},Code ile Rota Ara (Örn: IST -> ESB)
GET,/api/routes/search/name?dep={Name}&arr={Name},Name ile Rota Ara
GET,/api/flights/search/airline-name?airlineName={Name},Havayolu İsmine Göre Uçuşları Getir
GET,/api/flights/search/departure?airportName={Name},Kalkış Yerine Göre Uçuşları Getir
GET,/api/flights/search/arrival?airportName={Name},Varış Yerine Göre Uçuşları Getir
GET,/api/tickets/{ticketNumber},Bilet Detaylarını Getir (Bilet No ile)
POST,/api/airlines,Yeni Havayolu Şirketi Ekle
POST,/api/airports,Yeni Havaalanı Ekle (Code & Name)
POST,/api/routes,Yeni Rota Oluştur (Code kullanarak)
POST,/api/flights,Yeni Uçuş Oluştur (Fiyat ve Kota belirlenir)
POST,/api/tickets/buy,Bilet Satın Al (Fiyat hesaplanır & Kart maskelenir)
DELETE,/api/tickets/{ticketNumber},Bileti İptal Et
