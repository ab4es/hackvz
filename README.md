<h1>2015 HackVZ</h1>

This solution was the result of a 24-hour Hackathon event for Verizon Summer Interns during June 2015.

<b>Team:</b>
<ul>
	<li><b><a href="mailto:ab4es@virginia.edu">Aditya Bindra</a></b>  – Head Developer</li>
	<li><b>Evan Wolfe</b> – Project Manager</li>
	<li><b>Omar Warraky</b> – Researcher</li> 
</ul>

<b>Problem statement: </b>You and your friends decide to go on a road trip across the continental United States. You want to visit as many trademark locations as possible. You are given a list of locations that you can visit. Your objective is to create an optimal route to make the most of your trip. (There were several additional rules given – including duration constraints, speed limit, location duration, and weighted-point locations, amongst others – but I have chosen to leave them out for simplicity's sake. You can download the Hackathon's problem set files <a href = "https://drive.google.com/folderview?id=0Bya5T9Y9qhYIVVdHaklVLXJwMEE&usp=sharing">here</a>.)

<b>Approach: </b>Seeing that it was impossible to determine the most optimal solution due to our time and computing restraints, we decided to use a <a href = "https://en.wikipedia.org/wiki/Genetic_algorithm">genetic algorithm</a> to find a near optimal solution for this problem. Our genetic algorithm's process can be succinctly described in the following 6 processes.
<ul>
	<li>Initialization – After having imported all the locations from the given CSV, an initial population of randomly ordered trips was created.</li>
	<li>Evaluation – Each trip in the population was then graded using a custom fitness calculator. This scale ranked trips using a weighted scaled based on the proximity of its locations to one another, its location types, and total amount of states visited, amongst other weightings. See the Problem Statement file to further appreciate the fitness scale's weightages.</li>
	<li>Selection – To improve the overall population's fitness, the "strongest" trips (i.e., those with the highest fitness scores) from the initial population.</li>
	<li>Breeding – The strongest trips that were selected are then "bred" by taking the one leg of a trip and adding it to the leg of another trip so as to create a new trip that will, over several generations, be "bred" to be more fit. This is obviously putting it more simply than what the algorithm code will indicate, however.</li>
	<li>Mutation – Some trips are randomly mixed up to a small degree to add variation to the population. If this were not done, every solution we could create would already exist in our initial population.</li>
	<li>Rinse and repeat!</li>
</ul>
From generation to generation, the population was able to grow in strength to produce a near-optimal solution.
	
<b>One Major Shortcoming and its Inelegant Solution: </b>The Google Maps Distance Matrix API was used to calculate the distance between locations. Google, however, has justly imposed the following limits on its non-enterprise users:
<ul>			
	<li>100 elements per query.</li>
	<li>100 elements per 10 seconds.</li>
	<li>2500 elements per 24 hour period.</li>
</ul>
This initially severely impacted the solution as the genetic algorithm is heavily dependent on the amount of generations it can run. With these limitations, namely the last, the algorithm initially would often time-out as it was not able to calculate any more distances due to it reaching the daily limit.<br /><br />
The quick "hack" to somewhat alleviate this issue was to store all the distances between locations in a CSV file everytime the Google Maps Distance Matrix was queried. Then, instead of immediately looking to the Google Maps Distance Matrix to calculate the distance between two locations, the algorithm would instead first check to see if the aforementioned CSV file already had this distance stored. Although this was fairly inelegant, the time was running low at this point and the CSV quickly became more useful as its database grew due to the "stronger" trips having their distances stored.
