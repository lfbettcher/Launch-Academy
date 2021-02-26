import express from 'express'
import logger from 'morgan'
import path from 'path'
import { fileURLToPath } from 'url'

const __filename = fileURLToPath(import.meta.url)
const __dirname = path.dirname(__filename)

const app = express()

app.use(logger('dev'))
app.use(express.static(path.join(__dirname, '../public')))

app.listen(3000, '0.0.0.0', () => {
  console.log('Server is listening...')
})

export default app