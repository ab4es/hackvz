<h1>2015 HackVZ</h1>

This solution was the result of a 24-hour Hackathon event for Verizon Summer Interns during June 2015.

<b>Team:</b>
<ul>
	<li><b><a href="mailto:ab4es@virginia.edu">Aditya Bindra</a></b>  – Head Developer</li>
	<li><b>Evan Wolfe</b> – Project Manager</li>
	<li><b>Omar Warraky</b> – Researcher</li> 
</ul>

<b>Problem statement: </b>You and your friends decide to go on a road trip across the continental United States. You want to visit as many trademark locations as possible. You are given a list of locations that you can visit. Your objective is to create an optimal route to make the most of your trip. (There were several additional rules given – including duration constraints, speed limit, location duration, and weighted-point loctions, amongst others – but I have chosen to leave them out for simplicity's sake. You can download the Hackathon's problem set files <a href = "https://drive.google.com/folderview?id=0Bya5T9Y9qhYIVVdHaklVLXJwMEE&usp=sharing">here</a>.)

<b>Approach: </b>Seeing that it was impossible to determine the most optimal solution due to our time and computing restraints, we decided to use a <a href = "https://en.wikipedia.org/wiki/Genetic_algorithm">genetic algorithm</a> to find a near optimal solution for this problem. Our genetic algorithm's process can be succinctly described in the following 6 processes.
<ul>
	<li>Initialization – After having imported all the locations from the given CSV, an initial population of randomly ordered trips was created.</li>
	<li>Evaluation – Each trip in the population was then graded using a custom fitness calculator. This scale ranked trips using a weighted scaled based on the proximity of its locations to one another, its location types, and total amount of states visited, amongst other weightings. See the Problem Statement file to further apprciate the fitness scale's weightages.</li>
	<li>Selection – To improve the overall population's fitness, the "strongest" trips (i.e., those with the highest fitness scores) from the initial population.</li>
	<li>Breeding – The strongest trips that were selected are then "bred" by taking the one leg of a trip and adding it to the leg of another trip so as to create a new trip that will, over several generations, be "bred" to be more fit. This is obviously putting it more simply than what the algorithm code will indicate, however.</li>
	<li>Mutation – Some trips are randomly misordered to a small degree to add variation to the population. If this were not done, every solution we could create would already exist in our initial population.</li>
	<li>Rinse and repeat!</li>
</ul>
From generation to generation, the population was able to grow in strength to produce a near-optimal solution.
	
