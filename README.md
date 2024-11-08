Country Trips API

Provides a way to calculate the amount of trips that can be made in neighbouring countries:

Support POST request on /trips endpoint:
Example body:
{
	"startingCountry" : "australia",
	"budgetPerCountry" : 100,
	"totalBudget" : 1200,
	"currency" : "EUR"
}

Example output:
{
	"numberOfTrips": 2,
	"numberOfNeighbours": 5,
	"leftover": 200,
	"neighbours": [
		"GRC",
		"MKD",
		"ROU",
		"SRB",
		"TUR"
	]
}
