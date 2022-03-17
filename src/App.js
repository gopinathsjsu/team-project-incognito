import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import BookingRoutes from './routes/booking-routes.js';

function App() {
  return (
    <Router>
      <Switch>
        <BookingRoutes path="/" exact component={BookingSteps} />
      </Switch>
    </Router>
  );
}

export default App;
