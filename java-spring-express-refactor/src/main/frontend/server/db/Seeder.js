import pg from "pg"

const pool = new pg.Pool({
  connectionString: "postgres://postgres:password@localhost:5432/adopt_a_pet"
})

class Seeder {
  static async seed() {
    try {
      await pool.query(
        "INSERT INTO pet_types (type, img_url, description) VALUES "
          + "('cat', 'https://cdn.britannica.com/s:800x1000/22/206222-050-3F741817/Domestic-feline-tabby-cat.jpg', 'I own you'),"
          + "('dog', 'https://rb.gy/qkiiu0', 'Man''s best friend'),"
          + "('rabbit', 'https://rb.gy/e4cven', 'Thumper the super cool ski instructor')"
      )
      await pool.query(
        "INSERT INTO adoptable_pets (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id ) VALUES "
          + "('Sadie', 'https://g.petango.com/photos/1963/2b0f60ba-4da6-4b8d-b381-8f424eebcc35.jpg', 5, true, 'I originally came to PAWS from Hawaii, I was adopted but find myself back at PAWS because I am a princess who wants to be the only kitty in my castle and the center of your world.', 'adoptable', 1),"
          + "('Macklemeow', 'https://g.petango.com/photos/337/065a8037-c907-4f4b-90fb-50d643342b40.jpg', 4, true, 'I am currently in the process of being adopted!', 'pending', 1),"
          + "('Stanley', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGzHzNQvB7qNg05z1Un45cJpiCC7etIvjUJQ&usqp=CAU', 2, true, 'I would love a home with a lot of room to run around and lots of toys to play with and chew on!', 'available', 2),"
          + "('Odin', 'https://i.pinimg.com/564x/c0/eb/c0/c0ebc0e3d42726b68204435afe236135.jpg', 5, true, 'I would love a home with a lot of room to run around and lots of toys to play with and chew on!', 'available', 2),"
          + "('Trixie', 'https://g.petango.com/photos/337/35fdd1e7-6cb3-40c0-ba0d-59b967e9d3cc.jpg', 2, true, 'I am currently in the process of being adopted!', 'pending', 3),"
          + "('Benjamin', 'https://g.petango.com/photos/337/1d555baa-f4c7-456e-a2c4-c4361d0dcc46.jpg', 2, true, 'I would love a home with a lot of room to run around and lots of toys to play with and chew on!', 'available', 3)"
      )

      pool.end()
    } catch (error) {
      console.error(error)
      pool.end()
    }
  }
}

export default Seeder
