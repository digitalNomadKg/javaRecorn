# ✈️ Itinerary Prettifier Command Line Application

Application which reads a text-based itinerary from a file (input), processes the text to make it customer-friendly, and writes the result to a new file (output).

Built with Java 11+ , run via command line. 

 ---

 ## 📚 Tech Stack

| Technology          | Purpose                    |
|:--------------------|:---------------------------|
| Java                | Core backend framework     |
| CSV file            | Data manipulation          |
| input.txt           | Data input                 |
| output.txt          | Data output                |
|                     |                            |
---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone git@github.com:digitalNomadKg/javaRecorn.git
```
```bash
git clone https://github.com/digitalNomadKg/javaRecorn.git
```
```bash
cd itinerary
```
---

### 2. Build the java file

```bash
javac Prettifier.java
```
---

### 3. Update/add input.txt 

```bash
Flight from #LAX to #JFK departing at T24(2025-06-01T15:00:00-07:00).
Arrival at D(2025-06-01T23:30:00-04:00).
Layover at ##KLAX for 2 hours.

Continue to #ORD at T12(2025-06-02T06:00:00-05:00).
```
---

### 4. Run the application

```bash
Check quick usage using command below: 
```
```bash
java Prettifier.java -h 
```
---

```bash
Run the application.
```
```bash
java Prettifier.java input.txt output.txt airport-lookup.csv
```
---

### 5. Check output.txt

```bash
Flight from Los Angeles International Airport to John F Kennedy International Airport departing at 15:00 (-07:00).
Arrival at 01 Jun 2025.
Layover at Los Angeles International Airport for 2 hours.

Continue to Chicago O'Hare International Airport at 06:00AM (-05:00).
```
---

## 🐦‍🔥 Add your Pokémon.

```bash
So, if you are here then try to Add some VS Code Pokémon extension and enjoy your Pokemon. ⚡💥 
```
---