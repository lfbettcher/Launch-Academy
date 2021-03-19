//exports.
const seed = async () => {
  // truncate all existing tables
  // await knex.raw('TRUNCATE TABLE "books" CASCADE')

  return knex("books").insert([
    {
      id: 1,
      title: "Title 1",
      author: "Author 1",
      pageCount: 100,
      description: "Description 1",
      fiction: false,
    },
    {
      id: 2,
      title: "Title 2",
      author: "Author 2",
      pageCount: 200,
      description: "Description 2",
      fiction: true,
    },
    {
      id: 3,
      title: "Title 3",
      author: "Author 3",
      pageCount: 300,
      description: "Description 3",
      fiction: false,
    },
  ])
}

seed()