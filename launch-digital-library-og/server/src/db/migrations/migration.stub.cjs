/**
 * @typedef {import("knex")} Knex
 */

/**
 * @param {Knex} knex
 */
exports.up = async (knex) => {
  return knex.schema.createTable("books", (table) => {
    table.increments()
    table.string("title").notNullable()
    table.string("author").notNullable()
    table.integer("pageCount").notNullable()
    table.string("description")
    table.boolean("fiction")
    table.timestamp(true, true)
  })
}

/**
 * @param {Knex} knex
 */
exports.down = async (knex) => {
  return knex.schema.dropTableIfExists("books")
}
